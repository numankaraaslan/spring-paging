package com.numankaraaslan.paging;

import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataLoader implements CommandLineRunner
{
	private final SomeDataRepository someDataRepository;

	public InitialDataLoader(SomeDataRepository someDataRepository)
	{
		this.someDataRepository = someDataRepository;
	}

	@Override
	public void run(String... args) throws Exception
	{
		someDataRepository.deleteAll();
		Random rnd = new Random();
		int allItemsCount = 100;
		for (int i = 0; i < allItemsCount; i++)
		{
			String randomName = randomString(rnd, 10);
			someDataRepository.save(new SomeData(randomName));
		}
	}

	private String randomString(Random rnd, int length)
	{
		String letters = "abcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++)
		{
			sb.append(letters.charAt(rnd.nextInt(letters.length())));
		}
		return sb.toString();
	}
}
