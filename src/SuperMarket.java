import java.util.List;

public class SuperMarket {

    private final String name;
    private final String address;
    private final List<Department> departments;

    public SuperMarket(String name, String address, List<Department> departments) {
        this.name = name;
        this.address = address;
        this.departments = departments;
    }

    public boolean findProduct(String name) {
        for (Department department: departments) {
            if (department.findProduct(name)) {
                return true;
            };
        }
        return false;
    }

    @Override
    public String toString() {
        return "<<" + name + ">>";
    }

    public String getName() {
        return name;
    }

    public void printProducts() {
        for (Department department : departments) {
            department.printProducts();
        }
    }

    public boolean findDepartment(String departmentName) {
        for (Department department : departments) {
            if (department.getName().equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public Product getProductByName(String productName) {
        for (Department department : departments) {
            if (department.findProduct(productName)) {
                return department.getProductByName(productName);
            }
        }
        return null;
    }
}
