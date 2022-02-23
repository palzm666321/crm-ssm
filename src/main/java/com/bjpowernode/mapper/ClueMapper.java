package com.bjpowernode.mapper;

import com.bjpowernode.domain.Clue;
import com.bjpowernode.domain.User;

import java.util.List;
import java.util.Map;

public interface ClueMapper {

    int addClue(Clue clue);

    List<Clue> getAll();

    Clue getClueById(String id);

    int updateClue(Clue clue);

    int deleteClue(String id);
}
