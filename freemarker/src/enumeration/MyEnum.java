package enumeration;

public enum MyEnum {
    RED(1, "红色", 3),
    GREEN(2, "绿色", 4),
    BLUE(3, "蓝色", 5)
    ;

    private int id;
    private String name;
    private int index;

    MyEnum(int id, String name, int index) {
        this.id = id;
        this.name = name;
        this.index = index;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        MyEnum red = MyEnum.RED;
        System.out.println(red.getId());
        System.out.println(MyEnum.values());
    }
}
