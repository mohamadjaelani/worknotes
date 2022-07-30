### List Of Stream Example
#### 1. Remove duplicate element in Array
        public static void main(String[] args) {
          String[] nama = {"Jaelani","Indra","Fahmi","Indra","Fahmi","Jaelani","Indra","JaelanI"};
          TreeSet<String> ignoreCaseSenstive = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
          List<String> listName = Arrays.asList(nama);
          List<String> listDistinct = listName.stream()
                  .distinct()
                  .filter(n-> ignoreCaseSenstive.add(n))
                  .collect(Collectors.toList());
          System.out.println(listDistinct);
        }

  if not use ```ignoreCaseSensitive``` the results will be
  
        [Jaelani, Indra, Fahmi, JaelanI]

  if use ```ignoreCaseSensitive``` the results will be
  
        [Jaelani, Indra, Fahmi]
  #### other example
        public static void main(String[] args){
                Integer[] a = {5,2,3,2,4,5,4,3,1,2,6};
                List<Integer> lst = Arrays.asList(a);
                List<Integer> lstR = lst.stream().distinct().collect(Collectors.toList());
                System.out.println(lstR);
                // would be printed [5, 2, 3, 4, 1, 6]
                // the if want to sort
                lstR = lstR.stream().sorted().collect(Collectors.toList());
                System.out.println(lstR);
                // would be printed [1, 2, 3, 4, 5, 6]
        }
        
