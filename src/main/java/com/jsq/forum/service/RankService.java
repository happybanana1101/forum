package com.jsq.forum.service;

import com.jsq.forum.dao.PointsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RankService {
    @Autowired
    PointsDao pointsDao;


    public void addUser(String userId){
        pointsDao.addUser(userId);
    }

    public void changepoint(String userId,String option,Boolean useful){
        pointsDao.changePoint(userId,option,useful);
    }
    public void changepoint(String userId,String option){
        pointsDao.changePoint(userId,option,false);
    }

    public Double getPoint(String username){
        return pointsDao.getPoint(username);
    }

    public Set<String> getPointSet(){ return  pointsDao.getPointSet();}
}
