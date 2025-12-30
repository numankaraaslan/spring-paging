package com.numankaraaslan.paging;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SomeDataRepository extends JpaRepository<SomeData, Integer>, JpaSpecificationExecutor<SomeData>
{
}