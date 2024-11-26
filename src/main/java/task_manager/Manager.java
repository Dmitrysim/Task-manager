package task_manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private Map<Integer, Subtask> subtasks;
    private Map<Integer, Epic> epics;
    private int nextId = 1;

    public Manager() {
        subtasks = new HashMap<>();
        epics = new HashMap<>();
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
    }

    public int createEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
        return epic.getId();
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void deleteSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        updateEpicStatus(subtask.getEpicId());
    }

    public void deleteEpic(int id) {
        Epic epic = epics.remove(id);
        for (Subtask subtask : epic.getSubtasks()) {
            subtasks.remove(subtask.getId());
        }
    }

    private void updateEpicStatus(int epicId) {
        Epic epic = epics.get(epicId);
        if (epic.getSubtasks().stream().allMatch(s -> s.getStatus() == Status.DONE)) {
            epic.setStatus(Status.DONE);
        } else if (epic.getSubtasks().stream().anyMatch(s -> s.getStatus() == Status.IN_PROGRESS)) {
            epic.setStatus(Status.IN_PROGRESS);
        } else {
            epic.setStatus(Status.NEW);
        }
        updateEpic(epic);
    }

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }
}
