package shiro.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shiro.dao.ActionDAO;
import shiro.domain.Action;
import shiro.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

    @Autowired
    private ActionDAO actionDAO;

    @Override
    public Set<String> listActionNames(String userName) {

        List<Action> actionList = actionDAO.listActionsByUserName(userName);
        Set<String> result = new HashSet<>();
        for (Action action : actionList) {
            result.add(action.getActionName());
        }
        return result;
    }
}
