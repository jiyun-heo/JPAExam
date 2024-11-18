package com.fullstack.springboot;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	
	@Transactional
	//@Test
	void sell() { //Memo 엔티티에서 특정 key 를 기준으로 select 해보기
		Long mno = 100L;//찾을 키
		
		Memo memo = memoRepository.getReferenceById(mno); //사용하려면 반드시 transactional 을 선언해야함 
		System.out.println(memo);
		
//		Optional<Memo> optional = memoRepository.findById(mno);
//		if(optional.isPresent()) { //결과가 존재 한다면...
//			Memo memo = optional.get();
//			System.out.println(memo);
//		}
		
	}
	
	//이번엔 update 작업
	
//	@Test
//	void updateEntity() {
//		Memo memo = Memo.builder().memoText("이건 바뀐 내용입니다.").mno(100L).build();
//	
//		System.out.println(memoRepository.save(memo));
//	}
	
	@Test
	void delEntity() {
		Long mno = 100L;
		memoRepository.deleteById(mno);
	}

}
