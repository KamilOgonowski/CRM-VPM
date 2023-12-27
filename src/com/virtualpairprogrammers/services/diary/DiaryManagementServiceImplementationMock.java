package com.virtualpairprogrammers.services.diary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.virtualpairprogrammers.domain.Action;

public class DiaryManagementServiceImplementationMock implements DiaryManagementService {

	private Set<Action> allActions = new HashSet<>();

	public DiaryManagementServiceImplementationMock() {
	}

	@Override
	public void recordAction(Action action) {
		allActions.add(action);
	}

	@Override
	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> incompliteActions = new ArrayList<>();
		for(Action action : allActions) {
			if(action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
					incompliteActions.add(action);
				}
			}
		return incompliteActions;
}

}
