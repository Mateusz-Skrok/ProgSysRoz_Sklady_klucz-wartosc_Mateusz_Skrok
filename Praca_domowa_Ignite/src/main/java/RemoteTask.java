import Data.Student;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;

import javax.cache.Cache;

class RemoteTask implements IgniteRunnable {
    @IgniteInstanceResource
    Ignite ignite;

    @Override public void run() {
        double avg=0;
        IgniteCache<Integer, Student> students = ignite.getOrCreateCache("Students");

        for (Cache.Entry<Integer, Student> student : students) {
            avg += student.getValue().getBirthyear();
        }
        System.out.println("Sednia wieku studentów wynosi: "+avg/students.size() +" lat");
    }
}