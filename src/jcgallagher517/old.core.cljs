(ns jcgallagher517.core
    (:require
      [reagent.core :as r]
      [reagent.dom :as rd]
      [reitit.frontend.easy :as rfe]))

;; -------------------------
;; Views

(defn home-page []
  [:div
   [:h2 "Welcome to Reagent"]
   [:p "This is home page"]])

(defn about-page []
  [:div
   [:h2 "About me"]
   [:p "This is about page"]])

(defn physics-page [match]
  (let [{:keys [path query]} (:parameters match)
        {:keys [id]} path]
    (if (< id 1)
      [])))

;; -------------------------
;; Routes 



;; -------------------------
;; Old Routes 

;; (def main-pages                                                                        
;;   {:name "Home" :body home-page})                                                      
;;                                                                                        
;; (defn page-with-header [page]                                                          
;;   [(nav-bar main-pages)                                                                
;;    [page]])                                                                            
;;                                                                                        
;; (defn nav-element                                                                      
;;   [page]                                                                               
;;   [:button                                                                             
;;    {:type "button"                                                                     
;;     :on-click #(d/render (page-with-header page) (.getElementById js/document "app"))} 
;;     (:name page)])                                                                     
;;                                                                                        
;; (def nav-bar                                                                           
;;   [:header                                                                             
;;    [:h2                                                                                
;;     [:nav                                                                              
;;      [:div                                                                             
;;       (map nav-element main-pages)]]]])                                                

;; -------------------------
;; Initialize app

;; (defn mount-root []
;;   (d/render [home-page] (.getElementById js/document "app")))

;; (defn init! []
;;   (mount-root))

(def routes
  [["/"
    {:name ::home
     :view home-page}]

   ["/about"
    {:name ::about
     :view about-page}]

   ["/physics/:phys-id"
    {:name ::physics
     :view physics-page
     :parameters
     {:path {:id int?}
      :query {(ds/opt :foo) keyword?}}}]])

(defn init! []
  (rfe/start!
   router
   (fn [m] (reset! current-match m))
   {:use-fragment true})
  (rd/render [current-page] (.getElementById js/document "app")))
