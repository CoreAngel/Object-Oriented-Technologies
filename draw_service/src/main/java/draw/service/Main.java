package draw.service;

import draw.service.entity.EntityRepository;
import draw.service.system.EntityManager;
import draw.service.window.Window;

public class Main {

    public static void main(String[] args) {
        EntityManager em = new EntityManager();
        EntityRepository entityRepository = new EntityRepository();
        Window window = new Window(entityRepository, em);
    }
}
