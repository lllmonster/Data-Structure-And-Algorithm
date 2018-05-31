# log(n)
def binarySearch(list, target):
  lo = 0
  hi = len(list) - 1
  while lo <= hi:
    mid = (lo + hi) // 2
    if list[mid] == target:
      return mid
    elif list[mid] < target:
      lo = mid + 1
    else:
      hi = mid - 1
  return None
  
# test 
list = [1,2,3,4,5]
result = binarySearch(list,6)
print(result)