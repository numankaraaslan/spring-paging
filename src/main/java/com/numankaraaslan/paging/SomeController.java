package com.numankaraaslan.paging;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SomeController
{
	private final SomeDataRepository someDataRepository;

	public SomeController(SomeDataRepository someDataRepository)
	{
		this.someDataRepository = someDataRepository;
	}

	@GetMapping(path = { "", "/" })
	public ModelAndView dashboard(@RequestParam(name = "pg", required = false, defaultValue = "1") Integer pageNumber, @ModelAttribute(name = "searchdata") SearchData searchdata)
	{
		ModelAndView mav = new ModelAndView("deneme");
		int itemPerPage = searchdata.getPageSize();
		List<SomeData> data_list;
		long allItemsCount;
		// -------------------------- //
		Specification<SomeData> specification = SpecificationUtils.getSpecificationForSomeData(searchdata);
		Sort sorts = SpecificationUtils.getSortForSomeData(searchdata);
		data_list = someDataRepository.findAll(specification, PageRequest.of(pageNumber - 1, itemPerPage, sorts)).getContent();
		allItemsCount = someDataRepository.count(specification);
		paging_options(mav, pageNumber, allItemsCount, itemPerPage);
		mav.addObject("allItemsCount", allItemsCount);
		mav.addObject("other_params", searchdata.toString());
		mav.addObject("items", data_list);
		return mav;
	}

	private void paging_options(ModelAndView mav, int pageNumber, long allItemsCount, int pageSize)
	{
		int total_page_count = (int) Math.ceil((double) allItemsCount / pageSize);
		int midstart = Math.max(2, pageNumber - 2);
		int midend = Math.min(total_page_count - 1, pageNumber + 2);
		mav.addObject("midstart", midstart);
		mav.addObject("midend", midend);
		mav.addObject("page_count", total_page_count);
	}
}
