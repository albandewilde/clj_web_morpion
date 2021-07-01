FROM clojure

WORKDIR /usr/src/app

COPY . .

WORKDIR /usr/src/app/webmorpion

CMD ["lein", "run"]
