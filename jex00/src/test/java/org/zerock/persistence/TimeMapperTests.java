package org.zerock.persistence;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.config.RootConfig;
import org.zerock.mapper.TimeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Log4j2
public class TimeMapperTests {
    @Setter(onMethod_={@Autowired})
    private TimeMapper timeMapper;

//    @Test
//    public void testGetTime(){
//        log.info(timeMapper.getClass().getName());
//        log.info(timeMapper.getTime());
//    }

    @Test
    public void testGetTime2(){
        //mapper directory 만들때에는 한번에 하나의 폴더만 만들도록 한다.
        //여러개를 동시에 만들 경우 제대로 인식하지 못하는 문제가 발생.
        //org.zerock.mapper를 한번에 만들었더니 invalid bound statement 에러 발생하였음.
        log.info("getTime2");
        log.info(timeMapper.getTime2());
    }
}
