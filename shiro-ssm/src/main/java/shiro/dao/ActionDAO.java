package shiro.dao;

import java.util.List;

import shiro.domain.Action;

public interface ActionDAO {

    List<Action> listActionsByUserName(String userName);
}
