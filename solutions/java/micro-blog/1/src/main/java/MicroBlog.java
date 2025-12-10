class MicroBlog {
    public String truncate(String input) {
        int limit = 5;
        int cpCount = input.codePointCount(0, input.length());
        if(cpCount<=limit)
            return input;
        int endIndex = input.offsetByCodePoints(0, limit);
      return input.substring(0, endIndex);
    }
}
