package com.fullstack.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack.springboot.entity.Memo;

import jakarta.transaction.Transactional;

/*
 * Repository : hibernate 를 구현한 인터페이스...
 * 이 인터페이스는 내부적으로 ORM 매퍼를 구현한 인터페이스로, CRUD 를 구현할 수 있는 기능을 기본적으로 가지고 있음.
 * 이를 이용해서 CRUD 를 구현하고, 이를 컨트롤러에서 가져다 이 구현체를 DI 해서 CRUD 기능을 수행함.
 * 각종 상속을 통해서 우리가 주로 사용하는 interface 는 최하위인 JPARepository 임.
 * 우리가 이를 재상속을 하면 자동으로 CRUD 를 구현하는 하위타입 빈으로 설정됨.
 * 
 * CRUD 는 우리가 일반적으로 알고있는 sql 형태가 아닌, 메서드 형태로 되어있음.
 * select 는 find or get 형태로,
 * insert, update 는 save() 로... 만약 엔티티가 변경이 없다면, insert, 기존 entity 와 (proxy 로 관리되는) 비교해서,
 * 데이터가 변경이 있으면, update 로 delete 는 delete() 로 정의됨... 
 */

//JpaRepository<T, ID> => T 는 CRUD 할 Entity 명, ID 는 Entity 의 PK 타입을 지정합니다.
public interface MemoRepository extends JpaRepository<Memo, Long>{

	//쿼리 메서드는 여기에서 정의합니다.
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long start, Long end);

	//Pageable 을 파라미터로 주면서 Page 객체를 얻어내는 조합도 만들 수 있음..
	Page<Memo> findByMnoBetween(Long start, Long end, Pageable pageable);

	//쿼리메서드를 이용한 조건부 삭제 처리..
	void deleteMemoByMnoLessThan(Long mno);
	
	
	//@Query() : 실제 쿼리를 작성해서 수행하는 어노테이션..
	//아마 가장 많이 사용하지 않을까 함...
	//이 쿼리를 작성할 때 가장 주의 해야할 것은 반드시 대상이 테이블이 아닌 Entity 여야하고,
	//엔티티의 Alias 를 반드시 from 절에 줘야 함. 
	//객체지향의 원리를 관계형 DB 에 매핑한 쿼리임을 잊으면 안됨.
	
	@Query( value = "Select m from Memo m")
	List<Memo> getMemoList();
	
	//이번엔 동적 파라미터 바인딩 하는 방법..
	//?1,?2 등을 사용하는 방법... : xxx 와 같이 파람이름을 사용하는 방법, :#{} 같이 자바 빈객체처럼 사용하는 법
	//중 편한대로 사용하길...
	//update Memo m set m.memoText = :memoText where mno = :mno
	//메서드는 위 쿼리에 동적 파라미터를 받아서 매핑하도록 @Param("변수명") Long mno 을 사용해서 순서대로 매핑시킴..
	//DML을 사용할 때는 반드시 @Transactional 과 @Modifying 을 선언 해줘야 함.
	@Transactional
	@Modifying
	@Query(value = "Update Memo m Set m.memoText =:memoText where m.mno = :mno")
	int updateMemoText(@Param("mno") Long mno, @Param("memoText") String memoText);
	
	
	@Transactional
	@Modifying
	@Query(value = "Update Memo m Set m.memoText =:#{#param.memoText} where m.mno = :#{#param.mno}")
	int updateMemoTextMemoObj(@Param("param") Memo memo);
	
	//@Query 사용시에 Page 객체를 리턴받을 수 있는데, 당연히 Pageable 객체를 파라미터로 주면 됨.
	//단!!!!!!! Pageable 이 페이지수를 계산 하려면, 반드시 조회되는 목록수(count) 가 필요함.
	//때문에 이 쿼리를 반드시 수행해서 목록수를 같이 넘겨야 함.
	//이 쿼리는 countQuery 라는 속성값으로 사용하면됨. 
	@Query(value = "Select m from Memo m where m.mno > :mno", countQuery = "Select count(m) from Memo m where m.mno > :mno")
	Page<Memo> getListWithPage(@Param("mno")Long mno, Pageable pageable);
	
	//@Query native=true : 말 그대로, 실제 테이블을 대상으로 쿼리를 날림
	//우리가 사용하는 쿼리문을 그대로 사용할 수 있음.
	@Query(value = "Select * from tbl_memo where mno>0",nativeQuery = true)
	List<Memo> getNativeList();
	
	
	
}
