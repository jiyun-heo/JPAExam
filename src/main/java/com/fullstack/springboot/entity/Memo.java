package com.fullstack.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//★Entity 클래스는 테이블 하나의 1:1 매핑된다고 보면 됩니다.
//★우리가 SQL 을 보내게 되는 대상은 모두 Entity 입니다. 꼭 기억하세요.
//★Entity 로 선언된 클래스는 만약 테이블이 없다면, 실행시에 자동으로 생성되어집니다.
//Entity 클래스는 선언 후 프로그램이 시작되면, 자동 생성 또는 Alter 를 합니다.

@Entity //entity 로 선언
@Table(name = "tbl_memo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//★Entity 로 테이블 설계시 반드시 PK 를 지정해야 함. 안그럼 에러임★
public class Memo {
	
	//모든 숫자형 PK 는 객체 타입이어야 합니다.(주로 Long 사용함, 자동 형변형 없음.)
	@Id //pk 로 자동지정됨.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //DB 에서 Key 값 지정하도록 설정.
	private Long mno;
	
	//일반 컬럼 추가.. 자바 필드 선언처럼하되, @컬럼이라는 어노와 함께, 타입을 속성으로 지정합니다.
	@Column(length = 200, nullable = false)
	private String memoText;
	
}
