import os

import shutil

src = "./asynchttpclient/src/main/java/util/Util.java"

for root, dirs, files in os.walk("."):
  for file in files:
    if file == "Util.java":
      dest = os.path.join(root, file)
      if src == dest:
        continue
      print(dest)
      shutil.copy(src, dest)
