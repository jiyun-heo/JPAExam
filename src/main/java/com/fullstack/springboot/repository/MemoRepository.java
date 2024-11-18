package com.fullstack.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.springboot.entity.Memo;

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

}
