package zerobase.weather.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMemoRepositoryTest {
    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @DisplayName("01. insert, find by id")
    @Test
    public void test_01(){
        //given
        Memo memo = new Memo(2, "memo memo");

        //when
        jdbcMemoRepository.save(memo);

        //then
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "memo memo");
    }

    @DisplayName("02. find all")
    @Test
    public void test_02(){
        //given
        List<Memo> list = jdbcMemoRepository.findAll();
        //when
        System.out.println(list);
        //then
        assertNotNull(list);
    }
}