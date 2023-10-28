package com.cyk.springboot3.mongo.repository.impl;

import com.cyk.springboot3.mongo.entity.User;
import com.cyk.springboot3.mongo.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @author cyk
 * @date 2023/10/28 07:58
 */
@Component
public class UserRepositoryImpl implements UserRepository {


    //注入MongoTemplate
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加数据
     * save ⽅法中会进⾏判断，如果对象包含了 ID 信息就会进⾏更新，如果没有包含 ID 信息就⾃动保存。
     * @param user
     */
    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    /**
     * 根据⽤户名查询对象
     * @param userName
     * @return
     */
    @Override
    public User findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query , User.class);
        return user;
    }


    /**
     * 更新对象
     * 可以选择更新⼀条数据，或者更新多条数据
     * @param user
     * @return
     */
    @Override
    public long updateUser(User user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord"
                , user.getPassWord());
        //更新查询返回结果集的第⼀条
        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }

    /**
     * 删除对象
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }
}
