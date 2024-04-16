import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ChatHistoryTest.class, ChatServerTest.class, MessageMementoTest.class, MessageTest.class,
                SearchByUserTest.class, UserTest.class})
public class CompleteUnitTest
{

}
