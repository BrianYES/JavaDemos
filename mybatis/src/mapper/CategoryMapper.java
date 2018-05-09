package mapper;

import org.apache.ibatis.annotations.Insert;
import pojo.Category;

public interface CategoryMapper {

    @Insert("insert into category_ (name) values (#{name})")
    public void add(Category category);


}
