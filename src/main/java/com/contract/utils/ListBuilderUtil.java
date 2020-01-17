package com.contract.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * List builder.
 *
 * <ul>The main purposes of this builder is:
 * <li>simplify list creation and initialization.</li>
 * <li>replace unsafe generic varargs.</li>
 * </ul>
 *
 * <pre>
 *     // Create and initialize list
 *     List&lt;T&gt; list = ListBuilderUtil.create()
 *         .add(value1)
 *         .add(value2)
 *         .add(value3)
 *         .build();
 *
 *     // Possible unsafe operation
 *     method(List&lt;T&gt;... lists);
 *     method(list1, list2, list3); // implicit generics array creation!!!
 *
 *     // Replacement (more typing but safe)
 *     method(List&lt;List&lt;T&gt;&gt; lists);
 *     method(ListBuilderUtil.create().add(value1).add(value2).add(value3).build())
 * </pre>
 *
 * @author Q-APE
 */
public class ListBuilderUtil<T> {

    private List<T> list;

    /**
     * An alternative to constructor.
     *
     * @param list List a list to use
     * @return ListBuilderUtil list builder
     */
    public static <T> ListBuilderUtil<T> create(List<T> list) {
        return new ListBuilderUtil<T>(list);
    }

    /**
     * Creates list builder with {@link ArrayList}.
     *
     * @return ListBuilderUtil list builder
     */
    public static <T> ListBuilderUtil<T> newArrayList() {
        return new ListBuilderUtil<T>();
    }

    /**
     * Creates list builder with specified list.
     *
     * @param list List a list to use
     */
    public ListBuilderUtil(List<T> list) {
        this.list = list;
    }

    /**
     * Creates list builder with {@link ArrayList}.
     */
    public ListBuilderUtil() {
        this(new ArrayList<T>());
    }

    /**
     * Add value to list.
     *
     * @param value value
     * @return this buider
     */
    public ListBuilderUtil<T> add(T value) {
        list.add(value);
        return this;
    }


    /**
     * Build and return underlying list.
     *
     * @return List underlying list.
     */
    public List<T> build() {
        return list;
    }
}
