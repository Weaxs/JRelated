package reflect;

public class Father {

    private String field1;

    private String field2;

    public String field3;

    protected String field4;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    private String getField2() {
        return field2;
    }

    private void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }

    public void setField3(String field3) {
        this.field3 = field3;
    }

    protected String getField4() {
        return field4;
    }

    protected void setField4(String field4) {
        this.field4 = field4;
    }
}
