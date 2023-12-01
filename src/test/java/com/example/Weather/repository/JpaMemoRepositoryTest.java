package com.example.Weather.repository;

import com.example.Weather.domain.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional // test코드에서 transactional은 다 롤백처리를 해준다.
class JpaMemoRepositoryTest {
    @Autowired
    JpaMemoRepository jpaMemoRepository;
    @Test
    void insertMemoTest(){
        // given
        Memo memo = new Memo(1,"this is new memo");
        // when
        jpaMemoRepository.save(memo);
        // then
        List<Memo> memoList =jpaMemoRepository.findAll();
        assertTrue(memoList.size()>0);
    }

    @Test
    void findByIdTest(){
        // given
        Memo newMemo = new Memo(11,"jpa");
        // when
        Memo memo = jpaMemoRepository.save(newMemo);
        // then
        Optional<Memo> result = jpaMemoRepository.findById(11);
        assertEquals(memo.getText(),"jpa");
    }
}