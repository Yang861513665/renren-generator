package io.renren;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * @author yangxj
 * @date 2020-08-07 15:57
 *
 * 不需要spring环境的测试继承该基类
 */
public abstract class BaseMockTest {
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
}
