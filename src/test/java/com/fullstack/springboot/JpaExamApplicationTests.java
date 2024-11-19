package com.fullstack.springboot;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.fullstack.springboot.entity.Memo;
import com.fullstack.springboot.repository.MemoRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
class JpaExamApplicationTests {

	//CRUD 의 명세 역할을 하는 인터페이스가 뭐??
	@Autowired
	private MemoRepository memoRepository;
	
//	@Test
//	void testInsertDummies() {
//		//System.out.println(memoRepository.getClass().getName());
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			//builder() 를 이용해서 Memo entity의 setter 에 값을 set 한 후
//			//repo 의 save 를 이용해서 insert 함.
//			Memo memo = Memo.builder().memoText("Sample Text" + i).build();
//			memoRepository.save(memo);
//			
//		}); //stream 내부에 int 값 추출.
//	}
	
	//@Transactional
	//@Test
//	void sell() { //Memo 엔티티에서 특정 key 를 기준으로 select 해보기
//		Long mno = 100L;//찾을 키
//		
//		Memo memo = memoRepository.getReferenceById(mno); //사용하려면 반드시 transactional 을 선언해야함 
//		System.out.println(memo);
		
//		Optional<Memo> optional = memoRepository.findById(mno);
//		if(optional.isPresent()) { //결과가 존재 한다면...
//			Memo memo = optional.get();
//			System.out.println(memo);
//		}
//		
//	}
	
	//이번엔 update 작업
	
//	@Test
//	void updateEntity() {
//		Memo memo = Memo.builder().memoText("이건 바뀐 내용입니다.").mno(100L).build();
//	
//		System.out.println(memoRepository.save(memo));
//	}
	
//	@Test
//	void delEntity() {
//		Long mno = 100L;
//		memoRepository.deleteById(mno);
//	}
	
	//Pagination 처리용 유닛 테스트..
//	@Test
//	void testPageDefault() {
//		//JPA 의 findAll(Pageable) 을 이용하는데, Pageable 객체는 PageRequest 객체를 통해 얻어냄.
//		//이때, 필요에 따라서 Sort 객체를 생성 후 Pageable 객체 get 시 사용할 수 있음.
//		
//		//1페이지와, 페이지당 10개의 글 목록수 get
//		
//		Pageable pageable = PageRequest.of(0, 10);
//		
//		Page<Memo> page = memoRepository.findAll(pageable);
//		System.out.println(page);
//		
//		//Page 객체를 얻어냈으니, 페이지에 대한 정보등을 메서드를 통해 get 가능함.
//		System.out.println("---------------------------------");
//		System.out.println("Total Page : "+ page.getTotalPages());
//		System.out.println("총 Row 수 : " + page.getTotalElements());
//		System.out.println("현재 페이지 No : " + page.getNumber());
//		System.out.println("페이지당 목록 갯수 : " + page.getSize());
//		System.out.println("다음 페이지 존재 여부" + page.hasNext());
//		System.out.println("시작 페이지 여부 (0 Page) : " + page.isFirst());
//		
//		//가져온 목록 Entity 를 get 하고 , Entity 의 내용을 출력...
//		//getContent() 를 이용하면 됨.
//		page.getContent().forEach(ele -> System.out.println(ele.getMno() + ele.getMemoText()));
//	}
	
//	@Test
//	void testSorting() {
//		//mno desc Sort 객체 생성
//		Sort desSort = Sort.by("mno").descending();
//		Pageable pageable = PageRequest.of(1, 10, desSort);
//		Page<Memo> page = memoRepository.findAll(pageable);
//		
//		System.out.println("---------여긴 1 페이지로 desc 정렬된 목록들 -----------");
//		page.getContent().forEach(ele -> System.out.println(ele.getMno() + ele.getMemoText()));
//	}
	
	//JPARepositoy 의 query 메서드는 일반적인 CRUD 는 가능하나, 조건식이나, like 같은 연산자를 처리하는 메서드가 없음.
	//이를 위해서 쿼리메서드(QueryMethod) 나 @Query("실제 query") 를 이용해서 처리할 수 있음.
	
	//쿼리메서드는 메서드 이름 자체가 쿼리의 구문으로 처리되는 스프링의 기능...
	//나중에 배우겠지만 QueryDSL 등의 동적 쿼리 lib 를 사용하면, 더 확장된 기능의 쿼리를 수행할 수 있음.
	
	//쿼리 메서드를 이용한 조건절 처리..
//	@Test
//	void testQueryMethod() {
//		System.out.println("----------메서드 쿼리 실행결과------------");
//		memoRepository.findByMnoBetweenOrderByMnoDesc(10L, 20L).forEach(t->System.out.println(t));;
//	
//		//Sort 객체를 정의한 Pageable 객체를 주면서 Sorting 된 Page 객체를 쿼리메서드를 통해 get..
//		System.out.println("-----------페이지객체----------------");
//		Page<Memo> page = memoRepository.findByMnoBetween(10L, 50L, PageRequest.of(0, 10, Sort.by("mno").descending()));
//		page.forEach(ele->System.out.println(ele.getMno()));
//	}
	
//	@Test
//	@Commit //update와 select 제외한 얘들은 @commit 이랑 @transactional 같이 써줘야 함!
//	@Transactional //Select 와 Delete 가 동시에 처리되는 쿼리등에는 반드시 이 어노테이션이 들어가야 각각의 작업을 이어서 수행함.
//	void testDelByQueryMethod() {
//		System.out.println("---------delete 처리-----------");
//		memoRepository.deleteMemoByMnoLessThan(10L);
//	}
	
	//@Query 를 이용한 테스트.
//	@Test
//	void queryAnno1() {
//		memoRepository.getMemoList().forEach(t -> System.out.println(t.getMno()));
//	}
	
//	@Test
//	void queryAnnoUpdate() {
//		System.out.println(memoRepository.updateMemoText(99L, "이건 업데이트된 내용임"));
//	}
	
//	@Test
//	void queryAnnoUpdateMemoObj() {
//		System.out.println(memoRepository.updateMemoTextMemoObj(Memo.builder().mno(99L).memoText("졸리냐?").build()));
//	}
	
//	@Test
//	void queryAnnoPage() {
//		Pageable pageable = PageRequest.of(0, 10);
//		memoRepository.getListWithPage(0L, pageable).forEach(t -> System.out.println(t));
//	}

	@Test
	void naive() {
		System.out.println(memoRepository.getNativeList());
	}
	

}
