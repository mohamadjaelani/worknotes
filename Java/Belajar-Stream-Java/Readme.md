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
    
