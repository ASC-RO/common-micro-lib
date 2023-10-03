package micro.dao.criteria;

import java.io.Serializable;

/**
 * Implementation should usually contain fields of Filter instances.
 */
public interface Criteria extends Serializable {
    /**
     * <p>copy.</p>
     *
     * @return a new criteria with copied filters
     */
    Criteria copy();
}
