package task_manager;

public class Main {

    public static void main(String[] args) {

        Manager taskManager = new Manager();

        Epic epic1 = new Epic("Ремонт квартиры", "Сделать ремонт в новой квартире");
        int epic1Id = taskManager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Покраска стен", "Покрасить стены в квартире", epic1Id);
        taskManager.createSubtask(subtask1);

        Subtask subtask2 = new Subtask("Укладка ламината", "Уложить ламинат в квартире", epic1Id);
        taskManager.createSubtask(subtask2);

        Epic epic2 = new Epic("Подготовка к отпуску", "Собрать вещи и подготовиться к отпуску");
        int epic2Id = taskManager.createEpic(epic2);

        Subtask subtask3 = new Subtask("Купить чемодан", "Купить новый чемодан", epic2Id);
        taskManager.createSubtask(subtask3);

        System.out.println("\nСписок эпиков:");
        for (Epic e : taskManager.getAllEpics()) {
            System.out.println(e);
        }

        System.out.println("\nСписок подзадач:");
        for (Subtask s : taskManager.getAllSubtasks()) {
            System.out.println(s);
        }

        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);

        subtask2.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask2);

        System.out.println("\nОбновленный список эпиков:");
        for (Epic e : taskManager.getAllEpics()) {
            System.out.println(e);
        }

        System.out.println("\nОбновленный список подзадач:");
        for (Subtask s : taskManager.getAllSubtasks()) {
            System.out.println(s);
        }

        taskManager.deleteSubtask(subtask1.getId());
        taskManager.deleteEpic(epic2.getId());

        System.out.println("\nОкончательный список задач:");
        for (Subtask s : taskManager.getAllSubtasks()) {
            System.out.println(s);
        }

        System.out.println("\nОкончательный список эпиков:");
        for (Epic e : taskManager.getAllEpics()) {
            System.out.println(e);
        }
    }
}
