import com.hazelcast.aggregation.Aggregators;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.HazelcastInstanceAware;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.map.IMap;
import data.Student;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.concurrent.Callable;

public class HExecutorService {

    public HExecutorService() {
        ClientConfig clientConfig = null;
        try {
            clientConfig = HConfig.getClientConfig();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        final HazelcastInstance client = HazelcastClient.newHazelcastClient( clientConfig );

        IExecutorService executorService = client.getExecutorService("exec");
        executorService.submitToAllMembers(new HCallable());
    }
}

class HCallable implements Callable<Integer>, Serializable, HazelcastInstanceAware {
    private static final long serialVersionUID = 1L;
    private transient HazelcastInstance instance;

    @Override
    public Integer call() {
        IMap<Long, Student> students = instance.getMap("students");
        Set<Long> keys = students.localKeySet();
        System.out.println("Sredni wiek wszystkich studentow wynosi:"+students.aggregate(Aggregators.integerAvg("age")));
        return keys.size();
    }

    @Override
    public void setHazelcastInstance(HazelcastInstance instance) {
        this.instance = instance;
    }
}