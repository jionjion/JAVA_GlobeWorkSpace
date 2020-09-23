package composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 学院,管理下属院系
 *
 * @author Jion
 */
public class College extends OrganizationComponent {

    /** 包含院系, 用抽象类代替院系实现 */
    private List<OrganizationComponent> collegeList = new ArrayList<OrganizationComponent>();

    /** 构造器 */
    public College(String name, String description) {
        super(name, description);
    }

    /** 添加 */
    @Override
    protected void add(OrganizationComponent organizationComponent){
        collegeList.add(organizationComponent);
    }

    /** 删除 */
    @Override
    protected void remove(OrganizationComponent organizationComponent){
        collegeList.remove(organizationComponent);
    }

    /** 打印 */
    @Override
    public void print() {
        System.out.println("当前院系:" + collegeList.toString());
    }
}
