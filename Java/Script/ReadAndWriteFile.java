
private Map<String, String> readMapFromFile(String filePath) {
    Map<String, String> map = new HashMap<>();
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(filePath));
      String line = reader.readLine();
      while (line != null) {
        String[] pair = line.split("=", 2);
        map.put(pair[0], pair[1]);
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return map;
  }
  
  private void writeMapToGatewayhost(Map<String, String> map, String filePath) {
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter(filePath));
      StringBuilder sb = new StringBuilder();
      for (String key : map.keySet()) {
        sb.append(key + "=" + map.get(key));
        sb.append(System.lineSeparator());
      }
      bw.write(sb.toString());

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (bw != null) {
        try {
          bw.close();
        } catch (IOException e) {
          e.printStackTrace();
          logger.error("Writing map to file met IOException when close.");
        }
      }
    }
  }
