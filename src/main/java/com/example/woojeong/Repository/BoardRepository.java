package com.example.woojeong.Repository;

import com.example.woojeong.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

//  Entity기반 쿼리문
    @Modifying
    @Query(value = "UPDATE BoardEntity b set b.views = b.views + 1 where b.id = :id")
    void updateView(@Param("id") Long id);


}
