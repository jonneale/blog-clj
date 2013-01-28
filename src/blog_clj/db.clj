(ns blog-clj.db
  (:use [somnium.congomongo]
        [somnium.congomongo.config :only [*mongo-config*]]))
(def mongo-url
  (get (System/getenv) "MONGOHQ_URL"))


(defn split-mongo-url [url]
  "Parses mongodb url from heroku, eg. mongodb://user:pass@localhost:1234/db"
  (let [matcher (re-matcher #"^.*://(.*?):(.*?)@(.*?):(\d+)/(.*)$" url)] ;; Setup the regex.
    (when (.find matcher) ;; Check if it matches.
      (zipmap [:match :user :pass :host :port :db] (re-groups matcher)))))

(defn mongo-credentials
  []
  (if mongo-url
    (split-mongo-url mongo-url)
    {:user ""
     :pass ""
     :host "127.0.0.1"
     :db  "blog"
     :port "27017"}))

(defn maybe-init []
  "Checks if connection and collection exist, otherwise initialize."
  (when (not (connection? *mongo-config*))
    (let [config    (mongo-credentials)] ;; Extract options.
      (println "Initializing mongo @ " mongo-url)
      (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
      (authenticate (:user config) (:pass config))
      (and (or (collection-exists? :posts) (create-collection! :posts))
           (or (collection-exists? :visits) (create-collection! :visits))))))

(defn increment-visits!
  []
  (fetch-and-modify 
	 :visits
	 {:_id "counter"}
	 {:$inc {:value 1} }
	 :return-new true :upsert? true))

(defn visit-count
  []
  (let [v-count (fetch-one
               :visits
               :where {:_id "counter"})]
    (if v-count
      (:value v-count)
      0)
    ))
