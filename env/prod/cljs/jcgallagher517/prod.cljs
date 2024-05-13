(ns jcgallagher517.prod
  (:require [jcgallagher517.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
