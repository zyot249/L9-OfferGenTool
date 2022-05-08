package zyot.shyn.offergentool.pojo;

public class MyComboBoxItem<T> {
    private T id;
    private String name;

    public MyComboBoxItem() {
    }

    public MyComboBoxItem(T id, String name) {
        this.id = id;
        this.name = name;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
