package fritz.test.petclinic.services.map;

import fritz.test.petclinic.model.BasedEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BasedEntity, ID extends Long> {

	protected Map<Long, T> map = new HashMap<>();

	Set<T> findAll() {
		return new HashSet<>(map.values());
	}

	T findById(ID id) {
		return map.get(id);
	}

	T save(T object) {
		if (object != null) {
			if (object.getId() == null)
				object.setId(getNextId());
			map.put(object.getId(), object);
		} else {
			throw new RuntimeException("The object cannot be null");
		}

		return object;
	}

	void deleteById(ID id) {
		map.remove(id);
	}

	void delete(T object) {
		map.entrySet().removeIf(entry -> entry.getValue().equals(object));
	}

	private Long getNextId() {
		Long id = null;
		try {
			id = Collections.max(map.keySet()) + 1;
		} catch (NoSuchElementException e) {
			id = 1L;
		}
		return id;
	}

}
