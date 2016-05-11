package org.kriver.core.common;

public interface IndexedEnum <E extends Enum<E>> {

	/**
	 * 获取该枚举的索引值
	 * 
	 * @return 返回>=0的索引值
	 */
	public abstract int getIndex();

	public static class IndexedEnumUtil {
		/**
		 * 
		 * @param <E>
		 * @param enums
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> E[] toIndexes(E[] enums) {
			E[] _instance = (E[]) java.lang.reflect.Array.newInstance(enums[0].getClass(), enums.length);
			for (E _enum : enums) {
				_instance[_enum.getIndex()] = _enum;
			}
			for (int i = 0; i < _instance.length; i++) {
				if (_instance[i] == null) {
					throw new IllegalStateException("The enum at index [" + i + "] has not been set");
				}
			}
			return _instance;
		}
		
		/**
		 * 当枚举中有抽象方法的实现时，使用此方法生成
		 * 
		 * @param <E>
		 * @param clazz
		 *          Enum的class表示
		 * @param enums
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> E[] toIndexes(Class<E> clazz, E[] enums) {
			E[] _instance = (E[]) java.lang.reflect.Array.newInstance(clazz, enums.length);
			for (E _enum : enums) {
				_instance[_enum.getIndex()] = _enum;
			}
			for (int i = 0; i < _instance.length; i++) {
				if (_instance[i] == null) {
					throw new IllegalStateException("The enum at index [" + i + "] has not been set");
				}
			}
			return _instance;
		}

		/**
		 * 按索引取得指定的枚举
		 * @param <E>
		 * @param enums 枚举的数组
		 * @param index 索引 0 &lt;= index &lt; enums.length
		 * @return null,如果 0 &lt; index || index &gt; enums.lenth
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> E indexOf(final E[] enums, final int index) {
			return index >= 0 && index < enums.length ? enums[index] : null;
		}
		
		/**
		 * 根据传入的enums构建一个判断位
		 * @param <E>
		 * @param es
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> int createMask(E ...es){
			int mask = 0;
			for (E e : es) {
				mask |= (1 << e.getIndex());
			}
			return mask;
		}
		
		/**
		 * 根据判断位决定该Enum是否属于该判断位代表的类型
		 * @param <E>
		 * @param maskValue
		 * @param e
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> boolean isInMask(int maskValue,E e){
			return ((maskValue >> e.getIndex()) & 1) == 1;
		}
		
		/**
		 * 根据判断位决定该Index是否属于该判断位代表的类型
		 * @param <E>
		 * @param maskValue
		 * @param index
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public static <E extends IndexedEnum> boolean isInMask(int maskValue,int index){
			return ((maskValue >> index) & 1) == 1;
		}
	}
}
