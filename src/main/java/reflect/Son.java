package reflect;

public class Son extends Father {

    private String define1;

    public String define2;

    private Integer define3;

    protected String define4;

    public String getDefine1() {
        return define1;
    }

    public void setDefine1(String define1) {
        this.define1 = define1;
    }

    private String getDefine2() {
        return define2;
    }

    private void setDefine2(String define2) {
        this.define2 = define2;
    }

    public Integer getDefine3() {
        return define3;
    }

    public void setDefine3(Integer define3) {
        this.define3 = define3;
    }

    protected String getDefine4() {
        return define4;
    }

    protected void setDefine4(String define4) {
        this.define4 = define4;
    }
}
