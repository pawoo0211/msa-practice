rootProject.name = "ecommerce"

include("apps")
include("apps:cloud-hub")
include("apps:cloud-hub:discovery-service")
include("apps:cloud-hub:gateway-service")
include("apps:application")
include("apps:application:user-service")
include("apps:application:first-service")
findProject(":apps:application:first-service")?.name = "first-service"
include("apps:application:second-service")
findProject(":apps:application:second-service")?.name = "second-service"
