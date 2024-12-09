
public class testTemplate {
    public String ErrMessage = new String();
    public String TestName = new String();

    public String getErr(){
        return ErrMessage;
    }
    public String getName(){
        return TestName;
    }

    public boolean test() {
        ErrMessage="This is a generic error message";
        TestName = "testTemplate";

        return false;
    }

}
