def findSmallest(lst):
  smallest = lst[0]
  smallest_index = 0
  for i in range(len(lst)):
    print('com',lst[i],smallest)
    if lst[i] < smallest:
      smallest = lst[i]
      smallest_index = i
  return smallest_index
  
def selectionSort(lst):
  new_list = []
  while len(lst) > 0:
    smallest_index = findSmallest(lst)
    new_list.append(lst.pop(smallest_index))
    print(lst)
  return new_list

mylst = [1,2,3,4,1,2]
mynew_lst = []
mynew_lst = selectionSort(mylst)
print(mynew_lst)