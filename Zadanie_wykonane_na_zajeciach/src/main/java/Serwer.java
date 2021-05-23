import com.hazelcast.cluster.MembershipEvent;
import com.hazelcast.cluster.MembershipListener;
import com.hazelcast.config.Config;
import com.hazelcast.core.*;
import com.hazelcast.map.IMap;
import com.hazelcast.map.listener.EntryAddedListener;
import com.hazelcast.partition.MigrationListener;
import com.hazelcast.partition.MigrationState;
import com.hazelcast.partition.PartitionService;
import com.hazelcast.partition.ReplicaMigrationEvent;
import data.Prowadzacy;
import data.Student;
import data.Zajecia;

import java.net.UnknownHostException;

public class Serwer {
    public static void main(String[] args) throws UnknownHostException {
        Config config = HConfig.getConfig();

		HazelcastInstance instance = Hazelcast.newHazelcastInstance(config);

		instance.addDistributedObjectListener(new DistributedObjectListener() {

			@Override
			public void distributedObjectDestroyed(DistributedObjectEvent e) {
				System.out.println(e);
			}

			@Override
			public void distributedObjectCreated(DistributedObjectEvent e) {
				System.out.println(e);
			}
		});

		instance.getCluster().addMembershipListener(new MembershipListener() {

			@Override
			public void memberRemoved(MembershipEvent e) {
				System.out.println(e);
			}

			@Override
			public void memberAdded(MembershipEvent e) {
				System.out.println(e);
			}
		});

		PartitionService partitionService = instance.getPartitionService();
		partitionService.addMigrationListener(new MigrationListener() {
			
			@Override
			public void replicaMigrationFailed(ReplicaMigrationEvent e) {
				System.out.println(e);
			}
			
			@Override
			public void replicaMigrationCompleted(ReplicaMigrationEvent e) {
				System.out.println(e);
			}
			
			@Override
			public void migrationStarted(MigrationState s) {
				System.out.println(s);
			}
			
			@Override
			public void migrationFinished(MigrationState s) {
				System.out.println(s);
			}
		});

		IMap<Long, Student> students = instance.getMap("students");
		IMap<Long, Zajecia> zajecia = instance.getMap("zajecia");
		IMap<Long, Prowadzacy> prowadzacy = instance.getMap("prowadzacy");

		students.addEntryListener(new EntryAddedListener<Long, Student>() {

			@Override
			public void entryAdded(EntryEvent<Long, Student> e) {
				System.out.println(e);
			}
		}, true);


		zajecia.addEntryListener(new EntryAddedListener<Long, Zajecia>() {

			@Override
			public void entryAdded(EntryEvent<Long, Zajecia> e) {
				System.out.println(e);
			}
		}, true);

		prowadzacy.addEntryListener(new EntryAddedListener<Long, Prowadzacy>() {

			@Override
			public void entryAdded(EntryEvent<Long, Prowadzacy> e) {
				System.out.println(e);
			}
		}, true);

	}

}