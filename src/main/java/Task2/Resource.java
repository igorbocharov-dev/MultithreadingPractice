package Task2;

/**
 * Идемпотентная инициализация. getResource() создаёт дорогой объект ровно один раз даже при гонке десятков потоков,
 * дальше отдаёт готовый объект без блокировок
 */

public class Resource {

    private final int id;

    private Resource(int id) {
        this.id = id;
    }

    private static volatile Resource resource;

    public synchronized static Resource getResource(int id){
        if(resource == null){
            resource = new Resource(id);
            return resource;
        }
        return resource;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;
        return id == resource.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
