/*******************************************************************************
 * SORMAS® - Surveillance Outbreak Response Management & Analysis System
 * Copyright © 2016-2018 Helmholtz-Zentrum für Infektionsforschung GmbH (HZI)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package de.symeda.sormas.ui.task;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import de.symeda.sormas.api.task.TaskContext;
import de.symeda.sormas.api.user.UserRight;
import de.symeda.sormas.ui.ControllerProvider;
import de.symeda.sormas.ui.CurrentUser;
import de.symeda.sormas.ui.utils.AbstractView;

@SuppressWarnings("serial")
public class TasksView extends AbstractView {

	public static final String VIEW_NAME = "tasks";
	
	private final TaskGridComponent taskListComponent;

    public TasksView() {
    	super(VIEW_NAME);
    	
        taskListComponent = new TaskGridComponent(getViewTitleLabel());
        addComponent(taskListComponent);
        
    	if (CurrentUser.getCurrent().hasUserRight(UserRight.TASK_CREATE)) {
	    	Button createButton = new Button("New task");
	        createButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
	        createButton.setIcon(FontAwesome.PLUS_CIRCLE);
	        createButton.addClickListener(e -> ControllerProvider.getTaskController().create(TaskContext.GENERAL, null, taskListComponent.getGrid()::reload));
	        addHeaderComponent(createButton);
    	}
    }

    @Override
    public void enter(ViewChangeEvent event) {
    	taskListComponent.reload();
    	taskListComponent.updateActiveStatusButtonCaption();
    }
}
