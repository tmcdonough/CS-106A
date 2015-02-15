
public class ExpandableArray {
/**
* Creates a new expandable array with no elements.
*/
	public ExpandableArray() {
		arr = new Object[0];
	}
/**
* Sets the element at the given index position to the specified.
* value. If the internal array is not large enough to contain that
* element, the implementation expands the array to make room.
*/
	public void set(int index, Object value) {
		if(arr.length<index){
			Object[] newArr = new Object[index+1];
			for (int i = 0; i < arr.length;i++){
				newArr[i] = arr[i];
			}
			//for (int j = arr.length+1;j<=index;j++){newArr[j]=null;} - not necessary
			newArr[index] = value;
			arr = newArr;
		} else {
			arr[index] = value;
		}
	}
/**
* Returns the element at the specified index position, or null if
* no such element exists. Note that this method never throws an
* out-of-bounds exception; if the index is outside the bounds of
* the array, the return value is simply null.
*/
	public Object get(int index) {
		if (index>=arr.length){
			return null;
		} else {
			return arr[index];
		}
		
	}
	
	/* ivars */
	private Object[] arr;
}
