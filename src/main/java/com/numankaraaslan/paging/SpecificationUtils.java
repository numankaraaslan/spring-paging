package com.numankaraaslan.paging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SpecificationUtils
{
	public static Specification<SomeData> getSpecificationForSomeData(SearchData searchdata)
	{
		List<Specification<SomeData>> specs = new ArrayList<>();
		Specification<SomeData> spec = Specification.unrestricted();
		// name contains (case-insensitive)
		if (searchdata.getName() != null && !searchdata.getName().isBlank())
		{
			specs.add(new Specification<SomeData>()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<SomeData> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				{
					return cb.like(cb.lower(root.get("name")), "%" + searchdata.getName().toLowerCase() + "%");
				}
			});
		}

		// id >= minId
		if (searchdata.getMinId() != null)
		{
			specs.add(new Specification<SomeData>()
			{

				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<SomeData> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				{
					return cb.greaterThanOrEqualTo(root.get("id"), searchdata.getMinId());
				}
			});
		}

		// id <= maxId
		if (searchdata.getMaxId() != null)
		{
			specs.add(new Specification<SomeData>()
			{
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<SomeData> root, CriteriaQuery<?> query, CriteriaBuilder cb)
				{
					return cb.lessThanOrEqualTo(root.get("id"), searchdata.getMaxId());
				}
			});
		}

		// combine all with AND
		for (Specification<SomeData> s : specs)
		{
			if (spec == null)
			{
				spec = s;
			}
			else
			{
				spec = spec.and(s);
			}
		}

		return spec; // can be null if no filters -> repo.findAll(spec, pageable) will just ignore spec
	}

	public static Sort getSortForSomeData(SearchData searchdata)
	{
		String sort = searchdata.getSort();
		switch (sort)
		{
		case "idAsc":
			return Sort.by("id").ascending();
		case "idDesc":
			return Sort.by("id").descending();
		case "nameAsc":
			return Sort.by("name").ascending();
		case "nameDesc":
			return Sort.by("name").descending();
		default:
			return Sort.by("id").descending();
		}
	}
}