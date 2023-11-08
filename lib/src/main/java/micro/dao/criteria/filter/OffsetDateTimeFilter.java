package micro.dao.criteria.filter;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Filter class for {@link java.time.OffsetDateTime} type attributes.
 *
 * @see RangeFilter
 */
public class OffsetDateTimeFilter extends RangeFilter<OffsetDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * <p>Constructor for OffsetDateTimeFilter.</p>
     */
    public OffsetDateTimeFilter() {
    }

    /**
     * <p>Constructor for OffsetDateTimeFilter.</p>
     *
     * @param filter a {@link OffsetDateTimeFilter} object.
     */
    public OffsetDateTimeFilter(OffsetDateTimeFilter filter) {
        super(filter);
    }

    /** {@inheritDoc} */
    @Override
    public OffsetDateTimeFilter copy() {
        return new OffsetDateTimeFilter(this);
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setEquals(OffsetDateTime equals) {
        super.setEquals(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setNotEquals(OffsetDateTime equals) {
        super.setNotEquals(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setIn(List<OffsetDateTime> in) {
        super.setIn(in);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setNotIn(List<OffsetDateTime> notIn) {
        super.setNotIn(notIn);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setGreaterThan(OffsetDateTime equals) {
        super.setGreaterThan(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setLessThan(OffsetDateTime equals) {
        super.setLessThan(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setGreaterThanOrEqual(OffsetDateTime equals) {
        super.setGreaterThanOrEqual(equals);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    @DateTimeFormat(iso = ISO.DATE_TIME)
    public OffsetDateTimeFilter setLessThanOrEqual(OffsetDateTime equals) {
        super.setLessThanOrEqual(equals);
        return this;
    }

}
