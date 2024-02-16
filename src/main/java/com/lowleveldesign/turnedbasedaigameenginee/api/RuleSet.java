package com.lowleveldesign.turnedbasedaigameenginee.api;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.lowleveldesign.turnedbasedaigameenginee.game.Board;

public class RuleSet<T extends Board> implements Iterable<Rule<T>> {
    Set<Rule<T>> ruleList = new HashSet<>();

    public void add(Rule<T> rule) {
        ruleList.add(rule);
    }

    @Override
    public Iterator<Rule<T>> iterator() {
        return ruleList.iterator();
    }

}
